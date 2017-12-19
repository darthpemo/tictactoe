(ns tictactoe.game
  (:require-macros
    [cljs.core.async.macros :as async-macros])
  (:require [cljs.core.async :as async]))

(def game-commands-in (async/chan))

(def game-events-out (async/chan))

; -- Start Game Command --
;   x-player-id is the player id for the X player
;   0-player-id is the player id for the O player
;   client-handle is a client specified string that helps the client know it is their game
;     that was created.
(defrecord StartGameCommand [x-player-id, o-player-id, client-handle] )

; -- Take square command --
;   game-id is the game's id
;   player-id is the player requesting the move
;   player-type is either:
;     :o-player
;     :x-player
(defrecord TakeSquareCommand [game-id, player-id, player-type])

; -- Game Created Event
(defrecord GameCreatedEvent [game-id, x-player-id, o-player-id])


