(ns tictactoe.core
    (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)


;; define your app data so that it doesn't get over-written on reload

(defn new-board [n]
  (vec (repeat n :o-square)))

(defonce app-state (atom {:text "Tic Tac Toe"
                          :board (new-board 9) }))

(defn square-empty [row col]
  [:rect
   { :width 0.9
     :height 0.9
     :fill "grey"
     :x (+ 0.05 col)
     :y (+ 0.05 row)
     :on-click
     (fn empty-square-click [e]
       (prn "Empty square clicked at: " row col))}])


(defn square-circle [i j]
  [:circle
   {:r 0.35
    :stroke "green"
    :stroke-width 0.12
    :fill "none"
    :cx (+ 0.5 i)
    :cy (+ 0.5 j)}])

(defn square-cross [i j]
  [:g {:stroke "darkred"
       :stroke-width 0.4
       :stroke-linecap "round"
       :transform
       (str "translate(" (+ 0.5 i) "," (+ 0.5 j) ") "
            "scale(0.3)")}
   [:line {:x1 -1 :y1 -1 :x2 1 :y2 1}]
   [:line {:x1 1 :y1 -1 :x2 -1 :y2 1}]])


(defn tictactoe []
  [:div
   [:h1 (:text @app-state)]
   [:svg
    { :view-box "0 0 3 3"
      :width 500
      :height 500 }
    (for [row (range 3)
          col (range 3)]
      (let [position (+ (* row 3) col)]
        (case (get-in @app-state [:board position])
          :x-square [square-cross row col]
          :o-square [square-circle row col]
          :empty-square [square-empty row col])))]])


(reagent/render-component [tictactoe]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
