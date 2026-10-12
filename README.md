## autobg
autobg is a command-line program for automatically changing the wallpaper using `swaybg` and saving it on Wayfire's config.

The process of changing the current wallpaper is very manual on swaybg, especially on standalone window managers: it requires that you kill the current swaybg process, spawn a new one with the new file then disown the process on your shell and exit.
On window managers you then also have to update their config file by changing the swaybg command that runs on start to save the new wallpaper.
This program automates these tasks.

## Requirements
* Java 11 or newer
* [swaybg](https://github.com/swaywm/swaybg)
* [wayfire](https://github.com/WayfireWM/wayfire) (optional, config update is implemented for Wayfire only)

## Download

You can download autobg from the [releases page](https://github.com/spacebanana420/autobg/releases).

You can run `java -jar autobg.jar` and open the help screen to see what you can do.

### Install on your system (using [Yuuka](https://github.com/spacebanana420/yuuka))
```
yuuka install autobg.jar
```
