gulp = require "gulp"
http = require "http"
gls = require "gulp-live-server"


gulp.task "serve", ->
	server = gls.new('server.js', 3567)
	server.start()
	gulp.watch(['static/**/*.css', 'static/**/*.html'], server.notify)
	gulp.watch('server.js', server.start)
