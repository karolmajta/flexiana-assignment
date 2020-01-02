(ns flexiana-assignment.app
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<!]]
            [cljs-http.client :as http]
            [goog.string :as gstring]
            [goog.string.format]
            [reagent.core :as r]))

;; good enough for demo app, should be an envvar...
(def ^:private api-root "http://localhost:3000")

(defn text-input [{value :value name :name on-change :on-change disabled :disabled}]
  [:input {:type      "text"
           :name      name
           :value     value
           :disabled  disabled
           :on-change (fn [e] ((or on-change #()) (-> e .-target .-value)))}])

(defn fetch-scramble [{source :source target :target on-success :on-success on-error :on-error}]
  (go
    (let [response (<! (http/get (str api-root "/" source "/" target) {:with-credentials? false}))]
      (if (= (:status response) 200)
        (on-success (-> response :body :scramble))
        (on-error response)))))

(defn scramble-widget [app-state]
  (let [source (@app-state :source)
        target (@app-state :target)
        loading (@app-state :loading)
        submit-disabled (or loading (empty? source) (empty? target))]
    [:form {:on-submit (fn [e] (do
                                 (.preventDefault e)
                                 (swap! app-state assoc :loading true)
                                 (fetch-scramble {:source source
                                                  :target target
                                                  :on-error #(do
                                                               (.alert js/window "Error when communicating with the server.")
                                                               (swap! app-state assoc :loading false))
                                                  :on-success #(do
                                                                 (.alert js/window (gstring/format "\"%s\" %s a scramble of \"%s\"." source (if % "is" "is not") target))
                                                                 (swap! app-state assoc :loading false)
                                                                 (swap! app-state assoc :source "")
                                                                 (swap! app-state assoc :target ""))})))}
     [:h1 "Check if given word is a scramble!"]
     [:hr]
     [:div
      [:label {:for "source"} "Source word"]]
     [:div
      [text-input {:id "source" :name "source" :value source :disabled loading :on-change (fn [val] (swap! app-state assoc :source val))}]]
     [:div
      [:label {:for "target"} "Target word"]]
     [:div
      [text-input {:id "target" :name "target" :value target :disabled loading :on-change (fn [val] (swap! app-state assoc :target val))}]]
     [:div
      [:button
       {:type "submit"
        :disabled submit-disabled}
       (if (not loading) "Check!" "Checking...")]]]))

(r/render [scramble-widget (r/atom {:source "" :target "" :loading false})] (.getElementById js/document "app"))