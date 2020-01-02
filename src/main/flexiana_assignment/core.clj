(ns flexiana-assignment.core
    (:require
      [ring.middleware.params :refer [wrap-params]]
      [compojure.core :refer [defroutes GET ANY]]
      [flexiana-assignment.resources :refer [scramble?]]))

(defroutes api-routes
           (GET "/:source/:target" [source target] (scramble? source target))
           (ANY "/*" [] ""))

(def api-handler
  (-> api-routes
      wrap-params))