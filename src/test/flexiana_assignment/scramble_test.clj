(ns flexiana-assignment.scramble-test
  (:use [clojure.test])
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [flexiana-assignment.scramble :as s]))

;; some cases from the spec

(deftest world-example
  (is (s/scramble? "rekqodlw" "world")))

(deftest codewars-example
  (is (s/scramble? "cedewaraaossoqqyt" "codewars")))

(deftest katas-example
  (is (not (s/scramble? "katas" "steak"))))

;; example-based unit testing

(deftest same-string
  (is (s/scramble? "helloworld" "helloworld")))

(deftest substring-is-a-scramble
  (is (s/scramble? "helloworld" "lowo")))

(deftest anagram-is-a-scramble
  (is (s/scramble? "helloworld" "lelhwooldr")))

(deftest substring-anagram-is-a-scramble
  (is (s/scramble? "helloworld" "lwol")))

(deftest something-with-extra-letter-is-not-a-scramble
  (is (not (s/scramble? "helloworld" "lwolu"))))

(deftest something-with-more-same-letter-occurences-is-not-a-scramble
  (is (not (s/scramble? "helloworld" "helloworrld"))))

(deftest empty-string-is-scramble-of-anything
  (is (s/scramble? "helloworld" "")))

(deftest empty-string-is-scramble-of-empty-string
  (is (s/scramble? "" "")))

(deftest nothing-nonempty-can-be-a-scramble-of-empty
  (is (not (s/scramble? "" "hello"))))

;; property-based unit testing

(def ^:private test-runs-number 100)

(def valid-scrambles
  "Generator for creating valid scramble pairs."
  (gen/let [target gen/string
            additional gen/string]
    (let [source-unshuffled (str target additional)
          source (apply str (shuffle (or (seq source-unshuffled) [])))]
      [source target])))

(deftest returns-true-for-valid-scrambles
  (is
   ((tc/quick-check test-runs-number (prop/for-all [[source target] valid-scrambles] (s/scramble? source target))) :pass?)))

(def invalid-scrambles
  "Generator for creating invalid scramble pairs."
  (gen/let [target (gen/such-that #(not (empty? %)) gen/string-alphanumeric)
            additional-raw gen/string-alphanumeric]
    (let [chars-forbidden-in-additional (set target)
          additional (apply str (remove #(contains? chars-forbidden-in-additional %) additional-raw))
          source-unshuffled (str (apply str (rest (shuffle (seq target)))) additional)
          source (apply str (or (seq source-unshuffled) []))]
      [source target])))

(deftest returns-true-for-valid-scrambles
  (is
   ((tc/quick-check test-runs-number (prop/for-all [[source target] invalid-scrambles] (#(not (s/scramble? %1 %2)) source target))) :pass?)))