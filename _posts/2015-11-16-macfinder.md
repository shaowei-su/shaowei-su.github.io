---
layout: post
title: Display MAC os Finder path
---
###Terminal:
> defaults write com.apple.finder _FXShowPosixPathInTitle -bool TRUE;killall Finder

###Rollback
> defaults delete com.apple.finder _FXShowPosixPathInTitle;killall Finder
