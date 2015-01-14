module.exports = (callback) ->
	cordova.exec (trackObj) -> # Success Callback
		rootObj = {}

		# Organize Tracks in to Objects.
		for t in trackObj
			if t.artist not in Object.keys(rootObj)
				rootObj[t.artist] = {}

			if t.album not in Object.keys(rootObj[t.artist])
				rootObj[t.artist][t.album] = {}

			tData = {path: t.path, duration: t.duration, trackNumber: parseInt(t.tracknumber)}
			rootObj[t.artist][t.album][t.title] = tData


		# Sort Artists
		sortedObj = {}
		artists = Object.keys(rootObj)
		artists.sort()

		for artist in artists
			sortedObj[artist] = rootObj[artist]
		rootObj = sortedObj


		# Sort Albums
		for artist in Object.keys(rootObj)
			if Object.keys(rootObj[artist]).length > 1
				albums = Object.keys(rootObj[artist])
				albums.sort()

				sortedObj = {}
				for album in albums
					sortedObj[album] = rootObj[artist][album]

				rootObj[artist] = sortedObj


		# Sort Tracks
		for artist in Object.keys(rootObj)
			for album in Object.keys(rootObj[artist])
				tl = {}
				for track in Object.keys(rootObj[artist][album])
					tl[rootObj[artist][album][track]['trackNumber']] = track

				tlArray = Object.keys(tl)
				tlArray.sort (number1, number2) ->
		  		number1 - number2

				sortedObj = {}
				for tn in tlArray
					sortedObj[tl[tn]] = rootObj[artist][album][tl[tn]]
				rootObj[artist][album] = sortedObj

		callback null, rootObj

	, callback , "ListMusic", "listmusic", [] #Fail Callback, Java Class
