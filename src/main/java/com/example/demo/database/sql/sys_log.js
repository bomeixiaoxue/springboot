/*
 Navicat Premium Data Transfer

 Source Server         : 本地mongodb
 Source Server Type    : MongoDB
 Source Server Version : 30401
 Source Host           : localhost:27017
 Source Schema         : test

 Target Server Type    : MongoDB
 Target Server Version : 30401
 File Encoding         : 65001

 Date: 09/08/2019 15:46:17
*/


// ----------------------------
// Collection structure for sys_log
// ----------------------------
db.getCollection("sys_log").drop();
db.createCollection("sys_log");
