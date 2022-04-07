(ns scrambles.core-test
  (:require [clojure.test :refer :all]
            [scrambles.core :refer :all]))



(deftest scramble-test
  (testing "Define that letters on word 'a' are included on word 'b'"
    (let [words [["DOS" "SOD"] ["DDOSS" "SSODD"]]]
      (is (every? true? (map #(apply scramble %) words))))

    (let [words [["DOSS" "SOD"] ["DDOSS" "SSODDD"]]]
      (is (every? nil? (map #(apply scramble %) words))))))
