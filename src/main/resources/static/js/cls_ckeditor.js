function classicEditor(){
    ClassicEditor
                .create( document.querySelector( '#editor' ), {

                 })
                .then(editor => {
                    editoro = editor;
                    editor.plugins.get("FileRepository").createUploadAdapter = function (loader) {
                        return new CustomUploadAdapter(loader, "/images/board/press", function(result){
                            var fileSeq = isEmpty(result[0]) ? "noImage" : result[0];
                            var imageUrl = window.location.protocol + "//" + window.location.host + "/image/" + fileSeq;
                            return {default": imageUrl};
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
function UploadAdapter() {
    let CustomUploadAdapter = (loader, path, fn_resolve) => {
        this.constructor = (loader) => {
            this.loader = loader;
            this.path = path;
            this.fn_resolve = fn_resolve;
        };
        this.upload = () => {
            return new Promise((resolve, reject) => {
                this.xhr = ajax_file_upload({
                    loader: loader,
                    resolve: resolve,
                    reject: reject,
                    files: [loader.file],
                    path: path,
                    fn_resolve: (e) => {
                        e.lengthComputable && (loader.uploadTotal = e.total, loader.uploaded = e.loaded);
                    },
                    fn_resolve: reject
                });

            )};
        };
        this.abort = () => {
            return this.xhr.abort && this.xhr.abort();
        }
    };
}