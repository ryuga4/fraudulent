(ns fraudulent-checker.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::customer
 (fn [db]
   (get-in db [:credentials :customer])))

(re-frame/reg-sub
 ::user
 (fn [db]
   (get-in db [:credentials :user])))

(re-frame/reg-sub
 ::password
 (fn [db]
   (get-in db [:credentials :password])))

(re-frame/reg-sub
 ::status
 (fn [db] (get-in db [:credentials :status])))