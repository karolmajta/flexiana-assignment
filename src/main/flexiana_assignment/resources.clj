(ns flexiana-assignment.resources
  (:require
   [liberator.core :refer [defresource]]
   [flexiana-assignment.scramble :as scramble]))

(defresource scramble? [t1 t2]
  :available-media-types ["application/json"]
  :handle-ok (fn [_] {:scramble (scramble/scramble? t1 t2)}))