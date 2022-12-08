//document.write('<script src="/static/js/cls_ajax.js"></script>');
"use strict";

function MyCustomUploadAdapterPlugin(editor){
    editor.plugins.get("FileRepository").createUploadAdapter = (loader) => {
    return new MyUploadAdapter(loader);
    };

}

/*
    참고: https://m.blog.naver.com/jioness/221318925814
    https://simsimjae.tistory.com/340
*/

class MyUploadAdapter{
    constructor(loader){
        this.loader = loader;
    }

    upload(){
        return this.loader.file
            .then( file => new Promise((resolve, reject) =>{
                this._initRequest();
                this._initListeners(resolve, reject, file);
                this._sendRequest(file);
            }));
    }
    
    abort() {
        if(this.xhr){
            this.xhr.abort();
        }
    }

    _initRequest(){
        const xhr = this.xhr = new XMLHttpRequest();

        xhr.open('POST', '/image/upload', true);
        xhr.responseType = 'json';
    }

    _initListeners(resolve, reject, file){
        const xhr = this.xhr;
        const loader = this.loader;
        const genericErrorText = `Couldn't upload file: ${file.name}`
        xhr.addEventListener('error', () => reject(genericErrorText));
        xhr.addEventListener('abort', () => reject());
        xhr.addEventListener('load', ()=>{
            const response = xhr.response;

            if (!response || response.error){
                console.log(response)
                return reject(response&&response.error ? response.error.message : genericErrorText);
            }

            resolve({
                default:response.url
            });
        });

        if(xhr.upload){
            xhr.upload.addEventListener('progress', evt => {
                if (evt.lengthComputable){
                    loader.uploadTotal = evt.total;
                    loader.uploaded = evt.loaded;
                }
            });
        }
    }

    _sendRequest(file){
        const data = new FormData();
        data.append('upload', file);

        this.xhr.send(data);
    }
}