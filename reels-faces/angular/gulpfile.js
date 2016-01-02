var gulp = require('gulp');
var del = require('del');
var runSequence = require('run-sequence');
var plumber = require('gulp-plumber');

//typescript
var typescript = require('gulp-typescript');
//sass
var sass = require('gulp-sass');
//css
var minify = require('gulp-minify-css');
//jshint
var jshint = require('gulp-jshint');

//browserify
var browserify = require('browserify');
var tsify = require('tsify');
var source = require('vinyl-source-stream');
var buffer = require('vinyl-buffer');
var sourcemaps = require('gulp-sourcemaps');



var config = {
 pipePath: '../src/main/resources/META-INF/resources/angular',
 cleanPath: '../src/main/resources/META-INF/resources/angular/*'
}



//----------------
// angular.js
//----------------
// gulp.task('angular', function () {
//   gulp.src(['node_modules/angular/angular.js'])
//     .pipe(plumber({
//       handleError: function (err) {
//         console.log(err);
//         this.emit('end');
//       }
//     }))
//     .pipe(gulp.dest(config.pipePath));
// });


//---------------
// moduels
//---------------
gulp.task('module_typescript', function () {
 return gulp.src(['modules/**/*.ts','!modules/typings/**/*.ts'])
 		.pipe(plumber({
 			handleError: function (err) {
 				console.log(err);
 				 this.emit('end');
 			}
 		}))
 		.pipe(typescript({
 			sourceMap: false,     // (optional) Generates corresponding .map file.
 			target: 'ES5',        // (optional) Specify ECMAScript target version: 'ES3' (default), or 'ES5'
 			module: 'commonjs',   // (optional) Specify module code generation: 'commonjs' or 'amd'
 			noImplicitAny: false, // (optional) Warn on expressions and declarations with an implied 'any' type.
 			noResolve: false,     // (optional) Skip resolution and preprocessing.
 			removeComments: true, // (optional) Do not emit comments to output.
				// noImplicitAny: true
 		}))
 		// .pipe(jshint())
   	// .pipe(jshint.reporter('default'))
 		// .pipe(gulp.dest('public/modules'));
 });
gulp.task('browserifyModule',['module_typescript'], function() {
	return browserify({
			entries: 'modules/modules.ts',
			debug: true
		})
  .plugin(tsify)
		.bundle()
		.pipe(source('modules.js'))
		.pipe(buffer())
		.pipe(sourcemaps.init({loadMaps: true}))
		.pipe(sourcemaps.write('./'))
		.pipe(gulp.dest(config.pipePath));
});
gulp.task('sassModule', function () {
  gulp.src(['modules/modules.sass'])
    .pipe(plumber({
      handleError: function (err) {
        console.log(err);
        this.emit('end');
      }
    }))
    .pipe(sass())
    .pipe(gulp.dest(config.pipePath));
});



//---------------
// faces
//---------------
//typescriptをコンパイルする
gulp.task('typescript', function () {
 return gulp.src(['faces/**/*.ts','!faces/typings/**/*.ts'])
 		.pipe(plumber({
 			handleError: function (err) {
 				console.log(err);
 				 this.emit('end');
 			}
 		}))
 		.pipe(typescript({
 			sourceMap: false,     // (optional) Generates corresponding .map file.
 			target: 'ES5',        // (optional) Specify ECMAScript target version: 'ES3' (default), or 'ES5'
 			module: 'commonjs',   // (optional) Specify module code generation: 'commonjs' or 'amd'
 			noImplicitAny: false, // (optional) Warn on expressions and declarations with an implied 'any' type.
 			noResolve: false,     // (optional) Skip resolution and preprocessing.
 			removeComments: true, // (optional) Do not emit comments to output.
				// noImplicitAny: true
 		}))
 		// .pipe(jshint())
   	// .pipe(jshint.reporter('default'))
 		.pipe(gulp.dest(config.pipePath));
 });
gulp.task('browserify',['typescript'], function() {
	return browserify({
			entries: 'faces/faces.js',
			debug: true
		})
		.bundle()
		.pipe(source('bundle.js'))
		.pipe(buffer())
		.pipe(sourcemaps.init({loadMaps: true}))
		.pipe(sourcemaps.write('./'))
		.pipe(gulp.dest(config.pipePath));
});
 //sassをコンパイルする
 gulp.task('sass', function () {
   gulp.src(['faces/**/*.sass'])
     .pipe(plumber({
       handleError: function (err) {
         console.log(err);
         this.emit('end');
       }
     }))
     .pipe(sass())
     .pipe(gulp.dest(config.pipePath));
 });

//xhtmlを出力先にコピーする
gulp.task('xhtml', function () {
  gulp.src(['faces/**/*.xhtml'])
    .pipe(plumber({
      handleError: function (err) {
        console.log(err);
        this.emit('end');
      }
    }))
    .pipe(gulp.dest(config.pipePath));
});


gulp.task('clean', function () {
  del.sync([config.cleanPath], {force: true});
});

gulp.task('build', function () {
  // runSequence('clean',['browserify','browserifyEditor','sass','css','html','img']);
		runSequence('clean',['browserifyModule','sassModule','browserify', 'sass', 'xhtml']);
});
