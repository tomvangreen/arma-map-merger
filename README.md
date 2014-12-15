Arma 3 Mission Merger
=====================

The Arma 3 Mission Merger is a tool that should help mission designers to merge parts of their mission automatically without having to run arma.

Who are we?
-----------
We are two programmers who like arma and like to create arma missions. We just started and are getting used to it, but basically we are fans of the game :D


Why have we started this project?
---------------------------------

We have started building missions in arma. As probably many others do, we don't just create one map with all the contents, but we create smaller maps with the modules of the missions and merge them together. Doing this makes it a lot simpler to create a big map, as you can concentrate on the module you are working on. But this approach comes with one big short coming. Merging the maps is somewhat a hazzle. It is not a very hard thing to do, but it has much potential to screw things up. 

Now in our situation we currently work as a team of two on a mission which consists of 5 parts. We still manage to screw the merge up every second time. Sometimes we take an old version of a map, we merge in the wrong order, we forget the reassign the players after merge, the wrong scripts are copied to the merged folder.

In software development we try to introduce concepts to automate such processes. If the process is everytime the same there is a good chance that you can improve your productivity by automating the process. So I had a look at the mission.sqm file and I was nicely surprised that the sqm file is plain text and uses a clean structured format.

Where are we now?
-----------------
We did a basic proof of concept in loading and writing the mission file, but basically we have nothing yet :D Also please keep in mind that this is a hobby project, so while we like to put as much time into it as we can, sometimes there are other more important things to do ;)

What are the key features we are aiming at?
-------------------------------------------
- Merging multiple maps in the command line
- Provide an api to load mission files and expose them as model. (if we do this right, our tool could be reused by other tools as basis)
- Create configuration files for projects, which define what maps and how they should be merged.
- Give the authors fine control on what should be taken from which file (don't take the players from any than one file)
- We could address the short comings that player assignments are removed in the standard editor merge.
- Provide a dry run mode which only checks if the files can be merged.
- Validation output which shows conflicts and problems with the merge process or result.
- Configure how the scripts are copied. In the best case all scripts from all map files are copied. If a script exists multiple times, a check should be done to ensure that the script is the same in every map.

What are extend features we would like to pursue?
-------------------------------------------------
- Provide a nice gui for easy management of configurations and process jobs.
- Provide a way to backup/restore to an external location (use version control like svn or git to have better control over your files).
- Description.ext merger: Sometimes you define specific things in your description.ext for your map module. If you merge then to another map and forget to add this change to the main Description.ext, something might not work correctly. 
- ScriptManager: Define a set of scripts (probably files, so you can also manage description ext with this). Copy the scripts automatically to all the maps of your project. Nice feature could define different script sets. You could have one set for releases and one set for debugging. Need to test some new features, just deploy the debugging scripts and have fun. Tests completed and want to release a new version, deploye the release scripts without debug output.
- Mission Viewer: If we can read the mission format we could also do a basic mission viewer
- Mission Editor: In a very crude way ;) Just change the contents of single nodes. Or probably add even new ones. (very very very low priority ;) ).
- Script Editor: It would be nice to have an sqf editor with autocompletion, syntax highlighting etc. (This is even more out of scope than the mission editor, as there are already fine sqf templates for notepad++ or sublime).


When will the project be available to the public?
-------------------------------------------------
You can check out the source code at any time. We don't have any set time frame yet, on when we want to make a stable release, because it depends strongly on how well we progress with the tool. We don't want to make an official release until we are satisfied with the tools functionality and reliability for the use cases we use it for.

How to use this tool
--------------------
The simple answer is. You can't. In the current state we do some tests with existing sqm files, but it all happens externaly and this is no interface you could use to do this. This section will be updated once a runnable command line application is available.

Can you help?
-------------
Actually you can. This is just a hobby project so our resources are limited. We are thankful for suggestions and improvements. If you can code an can help, please get in touch first. If you cannot code and still want to help you can. We need feedback and test missions. Our project is very basic and currently only works with some of our test maps, which aren't that big. So additional missions would be nice to see where the limits are currently. Do you have a special way to author your missions which cannot be handled by our tool. Tell us about it, probably we can improve and adapt, so you can use the tool in the future. Also spread the word. If you don't create missions yourself but know someone who does, share this project with them ;)
