document.write('<script src="/static/js/cls_ajax.js"></script>');
"use strict";

function classicEditor(){
    console.log("맨위");
    ClassicEditor
                .create( document.querySelector( '#editor' ), {

                 })
                .then(editor => {
                    console.log("진행중");
                    editoro = editor;
                    editor.plugins.get("FileRepository").createUploadAdapter = function (loader) {
                        return new CustomUploadAdapter(loader, "/images/board/press", function(result){
                            var fileSeq = isEmpty(result[0]) ? "noImage" : result[0];
                            var imageUrl = window.location.protocol + "//" + window.location.host + "/image/" + fileSeq;
                            return {"default": imageUrl};
                        });
                    };
                    console.log('CKEditor 적용 완료');
                })
                .catch(error => {
                    console.error(error);
                });
}

/*
    참고: https://m.blog.naver.com/jioness/221318925814
*/
var CustomUploadAdapter = function(loader, path, fn_resolve) {
    this.constructor = function (loader) {
        this.loader = loader;
        this.path = path;
        this.fn_resolve = fn_resolve;
    };
    this.upload = function() {
        return new Promise(function (resolve, reject) {
            this.xhr = file_upload({
                loader: loader,
                resolve: resolve,
                reject: reject,
                files: [loader.file],
                path: path,
                fn_resolve: function(e) {
                    e.lengthComputable && (loader.uploadTotal = e.total, loader.uploaded = e.loaded);
                },
                fn_resolve: reject
            });

        });
    };
    this.abort = function() {
        return this.xhr.abort && this.xhr.abort();
    }
};

