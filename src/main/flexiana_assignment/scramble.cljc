(ns flexiana-assignment.scramble)

(defn- nil-safe-subtract [x y]
  (if (nil? y) x (- x y)))

(defn scramble?
  "Tests whether target can be built by selecting letters of source"
  [source target]
  (let [source-histogram (frequencies source)
        target-histogram (frequencies target)
        diffs (map
               (fn [[character occurences]] (nil-safe-subtract occurences (source-histogram character)))
               target-histogram)]
    (every? #(<= % 0) diffs)))