"use strict";

let { src, series, watch, dest }  = require('gulp'),
    sass    	= require('gulp-sass'),
    clean   	= require('gulp-clean-css'),
    rename  	= require('gulp-rename'),
    server  	= require('gulp-server-livereload'),
    autoprefix 	= require('gulp-autoprefixer');

let type = process.argv[3];
if(type !== undefined && type !== '-e') {
	throw "\x1b[31mInvalid Argiment: Use '-e' or nothing.\x1b[0m";
}

function compile(file) {
	console.log("compile");
	
	let pathArray = file.replace('src\\', '').split('\\'),
		filename = pathArray.pop(),
	 	path = pathArray.join("\\");


    let task = src(file).pipe(sass().on("error", sass.logError));

    if(type === undefined) {
		task = task.pipe(clean());
	}
	

	return task.pipe(autoprefix({
		add: true,
		cascade: false,
	}))
	.pipe(rename(filename.replace('.scss', '.min.css')))
	.pipe(dest(`assets\\${ path }`));
}

exports.serve = function() {
	watch('src/css/**.scss').on("change", compile);

	return src('assets')
		.pipe(server({
			livereload: true,
			directoryListing: false,
			open: false
		}));
};