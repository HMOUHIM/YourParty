# Dockerfile
FROM ubuntu:20.04

ENV MAVEN_VERSION 3.2.3

ENV TOMCAT_MAJOR_VERSION 9

ENV TOMCAT_MINOR_VERSION 9.0

RUN mkdir C:\Users\user\eclipse-workspace\Party

VOLUME /PARTY

