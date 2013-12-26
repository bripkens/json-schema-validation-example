(ns json-schema-validation.core-test
  (:require [cheshire.core :refer [generate-string]])
  (:use clojure.test
        json-schema-validation.core))

(deftest test-basic-validation
  (is (:success (validate "collection" (generate-string {:name "Foobar"})))))

(deftest test-missing-required-properties
  (is (not (:success (validate "collection" (generate-string {}))))))

(deftest test-sub-resource-missing-property
  (let [data {:name "foo"
              :images [{:blub "bla"}]}]
    (is (not (:success (validate "collection" (generate-string data)))))))

(deftest test-too-many-properties
  (let [data {:name "foo" :bla false}]
    (is (not (:success (validate "collection" (generate-string data)))))))
