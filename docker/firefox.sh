#!/bin/bash

: ${FIREFOX_VERSION:=56.0.1}

/opt/firefox/$FIREFOX_VERSION/firefox/firefox "$@"