var __indexOf = [].indexOf || function(item) { for (var i = 0, l = this.length; i < l; i++) { if (i in this && this[i] === item) return i; } return -1; };

module.exports = function(callback) {
  return cordova.exec(function(trackObj) {
    var album, albums, artist, artists, rootObj, sortedObj, t, tData, tl, tlArray, tn, track, _i, _j, _k, _l, _len, _len1, _len2, _len3, _len4, _len5, _len6, _len7, _m, _n, _o, _p, _ref, _ref1, _ref2, _ref3, _ref4, _ref5;
    rootObj = {};
    for (_i = 0, _len = trackObj.length; _i < _len; _i++) {
      t = trackObj[_i];
      if (_ref = t.artist, __indexOf.call(Object.keys(rootObj), _ref) < 0) {
        rootObj[t.artist] = {};
      }
      if (_ref1 = t.album, __indexOf.call(Object.keys(rootObj[t.artist]), _ref1) < 0) {
        rootObj[t.artist][t.album] = {};
      }
      tData = {
        path: t.path,
        duration: t.duration,
        trackNumber: parseInt(t.tracknumber)
      };
      rootObj[t.artist][t.album][t.title] = tData;
    }
    sortedObj = {};
    artists = Object.keys(rootObj);
    artists.sort();
    for (_j = 0, _len1 = artists.length; _j < _len1; _j++) {
      artist = artists[_j];
      sortedObj[artist] = rootObj[artist];
    }
    rootObj = sortedObj;
    _ref2 = Object.keys(rootObj);
    for (_k = 0, _len2 = _ref2.length; _k < _len2; _k++) {
      artist = _ref2[_k];
      if (Object.keys(rootObj[artist]).length > 1) {
        albums = Object.keys(rootObj[artist]);
        albums.sort();
        sortedObj = {};
        for (_l = 0, _len3 = albums.length; _l < _len3; _l++) {
          album = albums[_l];
          sortedObj[album] = rootObj[artist][album];
        }
        rootObj[artist] = sortedObj;
      }
    }
    _ref3 = Object.keys(rootObj);
    for (_m = 0, _len4 = _ref3.length; _m < _len4; _m++) {
      artist = _ref3[_m];
      _ref4 = Object.keys(rootObj[artist]);
      for (_n = 0, _len5 = _ref4.length; _n < _len5; _n++) {
        album = _ref4[_n];
        tl = {};
        _ref5 = Object.keys(rootObj[artist][album]);
        for (_o = 0, _len6 = _ref5.length; _o < _len6; _o++) {
          track = _ref5[_o];
          tl[rootObj[artist][album][track]['trackNumber']] = track;
        }
        tlArray = Object.keys(tl);
        tlArray.sort(function(number1, number2) {
          return number1 - number2;
        });
        sortedObj = {};
        for (_p = 0, _len7 = tlArray.length; _p < _len7; _p++) {
          tn = tlArray[_p];
          sortedObj[tl[tn]] = rootObj[artist][album][tl[tn]];
        }
        rootObj[artist][album] = sortedObj;
      }
    }
    return console.log(rootObj);
  }, callback, "ListMusic", "listmusic", []);
};
