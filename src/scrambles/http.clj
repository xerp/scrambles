(ns scrambles.http
  (:require [scrambles.core :refer :all]
            [scrambles.specs :refer :all]
            [reitit.ring :as ring]
            [reitit.coercion.malli]
            [reitit.ring.coercion :as rrc]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [muuntaja.core :as m]))

(defn http-response
  ([body http-code]
   {:status http-code
    :body   body})
  ([body]
   (http-response body 200)))

(def scramble-handler
  {:post {:parameters {:body-params post-request-map}
          :responses  {200 {:body post-response-map}}
          :handler    (fn [{{:keys [word1 word2]} :body-params}]
                        (let [response-data {:result (scramble word1 word2)}]
                          (http-response response-data)))}})

(def routes
  ["/api"
   ["/ping" (constantly (http-response "pong"))]
   ["/scramble" scramble-handler]])

(def route-options
  {:data {:coercion   reitit.coercion.malli/coercion
          :muuntaja   m/instance
          :middleware [muuntaja/format-middleware
                       rrc/coerce-exceptions-middleware
                       rrc/coerce-request-middleware
                       rrc/coerce-response-middleware]}})


(def app
  (ring/ring-handler
    (ring/router routes route-options)
    (ring/routes
      (ring/create-resource-handler {:path "/"})
      (ring/create-default-handler))))