package com.dustinblackman.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.provider.MediaStore;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;

public class ListMusic extends CordovaPlugin {

  public static final int READ_PERM = 0;
  public static int SECURITY_ERR = 2;
  private CallbackContext callback;

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    this.callback = callbackContext;

    if (hasReadPermission()) {
      ContentResolver cr = this.cordova.getActivity().getContentResolver();

      String[] projection = {
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.ALBUM,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.DATA,
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media.TRACK
      };

      Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
      String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
      String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
      Cursor cur = cr.query(uri, projection, selection, null, sortOrder);
      int count = 0;

      JSONArray songList = new JSONArray();

      if(cur != null)
      {
        count = cur.getCount();

        if(count > 0)
        {
          while(cur.moveToNext())
          {
            JSONObject data = new JSONObject();

            // String tSongID = cur.getString(0);
            String tArtist = cur.getString(1);
            String tAlbum = cur.getString(2);
            String tTitle = cur.getString(3);
            String tPath = cur.getString(4);
            String tDuration = cur.getString(5);
            String tTrackNumber = cur.getString(6);

            // data.put("songID", tSongID);
            data.put("artist", tArtist);
            data.put("album", tAlbum);
            data.put("title", tTitle);
            data.put("path", tPath);
            data.put("duration", tDuration);
            data.put("tracknumber", tTrackNumber);

            songList.put(data);

          }

        }
      }

      cur.close();

      callbackContext.success(songList);
    } else {
      getReadPermission();
    }
    return true;
  }

  private boolean hasReadPermission() {
      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
          return PackageManager.PERMISSION_GRANTED == cordova.getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
      else
          return true;
  }

  private void getReadPermission() {
      cordova.requestPermission(this, READ_PERM, Manifest.permission.READ_EXTERNAL_STORAGE);
  }

  public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {
      for(int r:grantResults)
      {
          if(r == PackageManager.PERMISSION_DENIED)
          {
              callback.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, SECURITY_ERR));
          }
      }
      switch(requestCode)
      {
          case READ_PERM:
              ContentResolver cr = this.cordova.getActivity().getContentResolver();

              String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.TRACK
              };

              Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
              String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
              String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
              Cursor cur = cr.query(uri, projection, selection, null, sortOrder);
              int count = 0;

              JSONArray songList = new JSONArray();

              if(cur != null)
              {
                count = cur.getCount();

                if(count > 0)
                {
                  while(cur.moveToNext())
                  {
                    JSONObject data = new JSONObject();

                    // String tSongID = cur.getString(0);
                    String tArtist = cur.getString(1);
                    String tAlbum = cur.getString(2);
                    String tTitle = cur.getString(3);
                    String tPath = cur.getString(4);
                    String tDuration = cur.getString(5);
                    String tTrackNumber = cur.getString(6);

                    // data.put("songID", tSongID);
                    data.put("artist", tArtist);
                    data.put("album", tAlbum);
                    data.put("title", tTitle);
                    data.put("path", tPath);
                    data.put("duration", tDuration);
                    data.put("tracknumber", tTrackNumber);

                    songList.put(data);

                  }

                }
              }

              cur.close();

              callback.success(songList);
          break;
      }
  }

};
