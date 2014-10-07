android-asset-copier
====================

Copy drawables from a zipped package on to your android project tree.

Inspiration
--------------

I have used the [Android Asset Studio](http://romannurik.github.io/AndroidAssetStudio/index.html) a lot (for now it has been for my personal projects) and I find myself following a particular routine:

* Choose asset category and edit choice as desired
* Download asset zipped packged.
* Extract and do a copy of each asset item to my IDE (Eclispe mostly)
 
I have to say I find this sort of thing cumbersome/tedious so I created a quick Java application to do the job for me. I no longer need to extract zipped packages. The application allows me to download as many asset packs as I like and handle (transfer each asset to approprite folders) all in one click thereby avoiding the tedious task of extracting and copying individual assets to my IDE.

It occurred to me recently that another developer might like it. Perhaps a similar app exists.

New
---------------
I have now exposed a command-line interface. Let say you have a pack at `'/<path>/ic_rating_good.zip'` and you want to copy it to your project at `'/<project path>/res'`, type on a command-line interface:
`java -jar <jarfile> '/<path>/ic_rating_good.zip' '<project path>/res' -console`. That does it. You can pass `-Q' to turn off 'Type enter key to exit'. 

Future Features
-----------------
- Ability to send 'overwrite' flags to the command-line interface
- Handling of multiple packs throught the command-line interface
