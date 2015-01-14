Android List Music plugin
====================

A Cordova/Phonegap/SteroidsJS plugin that lists all tracks in Android's MediaStore (default music player).

This plugin is still in development and shouldn't be considered in a production environment.

##Overview

The plugin outputs Artist, Album, Track Name, Track Number, Track Duration, and File Path all in an easy to read Object. Artists and albums keys are sorted alphabetically, while tracks are sorted by track number.

```json
{
  "Arist": {
    "Album": {
      "Track 1": {
        "path": "/path/to/song.mp3",
        "duration": "12345",
        "trackNumber": 1
      },
      "Track 2": {
        "path": "/path/to/song.mp3",
        "duration": "12345",
        "trackNumber": 1
        },
    }
  }
}
```


##Usage

<a name="listMusic" />
### listMusic(err, callback)

List All Tracks on Phone

__Example:__

```javascript
listMusic(function(err, cb) {
  if (err) {
    console.log(err);
  }
  return console.log(cb);
});
```

__Output:__
```json
{
  "Asking Alexandria": {
    "From Death To Destiny": {
      "Don't Pray For Me": {
        "path": "/storage/emulated/0/Music/From Death To Destiny/01 Don't Pray For Me.mp3",
        "duration": "280425",
        "trackNumber": 1
      },
      "Killing You": {
        "path": "/storage/emulated/0/Music/From Death To Destiny/02 Killing You.mp3",
        "duration": "191922",
        "trackNumber": 2
      },
      "The Death Of Me": {
        "path": "/storage/emulated/0/Music/From Death To Destiny/03 The Death Of Me.mp3",
        "duration": "258952",
        "trackNumber": 3
      },
      "Run Free": {
        "path": "/storage/emulated/0/Music/From Death To Destiny/04 Run Free.mp3",
        "duration": "250776",
        "trackNumber": 4
      }
    }
  },

  "Beartooth": {
    "Disgusting": {
      "The Lines": {
        "path": "/storage/emulated/0/Music/Bear Tooth/Beartooth - Disgusting [2014]/01 The Lines.mp3",
        "duration": "228336",
        "trackNumber": 1
      },
      "Beaten In Lips": {
        "path": "/storage/emulated/0/Music/Bear Tooth/Beartooth - Disgusting [2014]/02 Beaten In Lips.mp3",
        "duration": "213768",
        "trackNumber": 2
      },
      "Body Bag": {
        "path": "/storage/emulated/0/Music/Bear Tooth/Beartooth - Disgusting [2014]/03 Body Bag.mp3",
        "duration": "230976",
        "trackNumber": 3
      }
    },
    "Sick EP": {
      "I Have a Problem": {
        "path": "/storage/emulated/0/Music/Bear Tooth/Sick EP/01 I Have a Problem.m4a",
        "duration": "241209",
        "trackNumber": 1
      },
      "Go Be The Voice": {
        "path": "/storage/emulated/0/Music/Bear Tooth/Sick EP/02 Go Be The Voice.m4a",
        "duration": "243972",
        "trackNumber": 2
      }
    }
  }
}
```
---------------------------------------
