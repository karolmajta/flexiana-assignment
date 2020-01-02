(ns flexiana-assignment.core
    (:require [flexiana-assignment.scramble :as scramble]))

(defn -main
      "I can say 'Hello World'."
      []
      (println (scramble/scramble? "afegbcde" "efg")))