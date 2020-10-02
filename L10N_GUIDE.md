## _L10N\_GUIDE for The Listeners_
Firstly: big thanks, Mariana and Gabriel, for working on Spanish and French translations of _The Listeners_.

Why L10N or l10n? This is geek-speak for 'localization' (L plus 10-letters plus N).

If you've found the repo(sitory) and then click on `Code`, you can then click down into the code by clicking the folder path `src/main/java/listeners`.

There it is further organized into four folders, one of which is `l10n`. This is the one in which you can work. Click ...

Inside `l10n` you'll find a whole bunch of `.java` files, some of them in sets. The names for these sets of files are partly the same, distinguished only by a suffix of the form `_xx_XX`.

Let's stick, for now, with the `CreepIntent` set. You'll see that, for this set I've added files with `_es_ES` and `_fr_FR`. The meaning is, respectively `spanish` as spoken in `SPAIN` and `french` as spoken in `FRANCE`.

The `CreepIntent.java` file is actually `[_en_GB]`, `english` as spoken in `GREAT BRITAIN`, which I'm afraid I made my default, hence no suffix (and it is also actually used as the fallback whenever appropriately suffixed files are not found).

At the moment, the extra files for `CreepIntent` are just copies of the default, British English, files. As soon as you're ready, and soon as you have Github account that I have made a `collaborator` on `thelisteners` repo, you will be able to go into one of these files and edit it online, translating my British English in place, in the repo itself. Instead of 'saving' you'll do the equivalent for this Github world, you'll 'Commit' using a button at the bottom of the editing window. So long as you work exclusively on your languages' files there is no risk to the code, by the way, although there would be even more secure and distributed workflows.

OK. I'm sure you've guessed: Yes, there is provision in Alexa's universe for `_fr_CA`: `french` as spoken in `CANADA`, and both `_es_MX` and `_es_US`: `spanish` as spoken in `MEXICO` and ? the `US`. But to do seperate files for these variants obviously considerably adds to your potential work. We'll have to discuss what you'd like to do about this. As you see I have made `_en_US` versions for many `Intents` but these are sometimes just copies of my `[default _en_GB]`. I can also make my code, for example, redirect French Canadian users to the `_fr_FR` responses.

To get started, why not just try editing your (one each) `es` or `fr` files for `CreepIntent` and we'll take it from there.

You'll also need to work out what's going on with the actual code-driven string manipulation of the Java inside the files. This may already make sense to you and, if not, it's probably best if you Zoom-meet with me to talk you through the (relatively simple) ways in which this code works.

