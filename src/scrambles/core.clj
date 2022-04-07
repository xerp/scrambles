(ns scrambles.core
  (:require [clojure.string :refer [lower-case]]
            [malli.core :as m]
            [scrambles.specs :refer [only-string]]))


(defn- coll-equal?
  "Check if collections have the same length."
  [coll]
  (let [quantities (map count coll)
        [first-value :as all] quantities]
    (every? #(= first-value %) all)))


(defn- character-frequency
  "Create a map with character and frequencies of each character."
  [word]
  (let [characters (-> word sort vec)
        map-frequency (reduce (fn [m c]
                                (let [c (lower-case c)]
                                  (assoc m c (inc (m c 0)))))
                              {} characters)]
    map-frequency))

(defn scramble
  "Define is the letters on word-a are included on word b"
  [word-a word-b]
  (let [string-valid? #(m/validate only-string %)]

    (if-not (and (every? string-valid? [word-a word-b]) (coll-equal? [word-a word-b]))
      false
      (let [word-frequencies (map character-frequency [word-a word-b])
            frequencies (map vals word-frequencies)
            [word-a-frequency word-b-frequency] frequencies]

        (every? true? (map = word-a-frequency word-b-frequency))))))
