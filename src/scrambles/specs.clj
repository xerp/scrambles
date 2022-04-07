(ns scrambles.specs
  (:require [malli.core :as m]))

(def only-string (m/schema :string))

(def post-request-map [:map
                       [:word1 only-string]
                       [:word2 only-string]])

(def post-response-map [:map
                        [:result boolean?]])
