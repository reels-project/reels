[![Stories in Ready](https://badge.waffle.io/reels-project/reels.png?label=ready&title=Ready)](https://waffle.io/reels-project/reels)
# Reels Framework

[![Build Status](https://travis-ci.org/reels-project/reels.svg)](https://travis-ci.org/reels-project/reels)
[![Coverage Status](https://coveralls.io/repos/reels-project/reels/badge.svg?branch=master&service=github)](https://coveralls.io/github/reels-project/reels?branch=master)

##前提

 - Java8
 - JavaEE7

##MVC？

JSFの世界ではM=BackingBean V=facelets C=FacesServletとされていますが、MVVMとしても認識できます(M=other java V=facelets VM=BakingBean)。MVCといってもそこから派生した考え方が多数あり捉え方はフレームワークや使う人によって様々です。  

ReelsではJSFをMVVMと捉えつつもVMの部分を`ViewModel`という曖昧なワードを使わずにあえて慣れている`Controller`としています。

 - M=other java
 - V=facelets
 - VM=Controller(BakingBean)

##ルーター

JSFのデフォルトの振る舞いではURLがユーザーフレンドリーではありません。
JSFの対象としてFacesServletに認識させるために`/faces/*`や`*.xhtml`といた設定をするため`http://xx.com/context/faces/product/list`や`http://xx.com/context/product/list.xhtml`といったURLになってしまいます。また、ウェブコンテンツルートからURLと同様のパスでファイルを設置しなければならないといった制約もあります。  

Reelsではルーター機能を追加して、ユーザーフレンドリーなURLとファイルの置き場所の自由を提供します。

##How to import to eclipse?
File > Import > Maven > Existing Maven Projects

