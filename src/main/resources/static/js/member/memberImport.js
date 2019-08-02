var memberImport = function () {


    function initPage() {
        $("#input-import").fileinput({
            language: 'zh', //设置语言
            dropZoneTitle: '可以将图片拖放到这里 …支持多文件上传',
            uploadUrl: contentPath + "file/upload", //上传的地址
            uploadExtraData: function (previewId, index) {   //该插件可以向您的服务器方法发送附加数据。这可以通过uploadExtraData在键值对中设置为关联数组对象来完成。所以如果你有设置uploadExtraData={id:'kv-1'}，在PHP中你可以读取这些数据$_POST['id']
                var id = $('#id').val();
                return {seriesId: id};
            },
            allowedFileExtensions: ['csv', 'xlsx', 'xls'],//接收的文件后缀
            uploadAsync: true, //默认异步上传
            showUpload: true, //是否显示上传按钮
            showRemove: true, //显示移除按钮
            showPreview: true, //是否显示预览
            showCancel: true,   //是否显示文件上传取消按钮。默认为true。只有在AJAX上传过程中，才会启用和显示
            showCaption: true,//是否显示文件标题，默认为true
            browseClass: "btn btn-primary", //文件选择器/浏览按钮的CSS类。默认为btn btn-primary
            dropZoneEnabled: true,//是否显示拖拽区域
            minImageWidth: 50, //图片的最小宽度
            minImageHeight: 50,//图片的最小高度
            maxImageWidth: 1000,//图片的最大宽度
            maxImageHeight: 1000,//图片的最大高度
            maxFileSize: 1024,//单位为kb，如果为0表示不限制文件大小
            minFileCount: 1, //每次上传允许的最少文件数。如果设置为0，则表示文件数是可选的。默认为0
            maxFileCount: 1, //每次上传允许的最大文件数。如果设置为0，则表示允许的文件数是无限制的。默认为0
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",//字符串，当文件数超过设置的最大计数时显示的消息 maxFileCount。默认为：选择上传的文件数（{n}）超出了允许的最大限制{m}。请重试您的上传！
        }).on('filepreupload', function (event, data, previewId, index) {     //上传中
            console.log('文件正在上传');
        }).on('fileuploaded', function (event, data,previewId, index) { //该方法将在上传成功后触发
            if(data.response.code === 0){
                swal("成功", data.response.msg, "success");
            }else{
                swal("失败", data.response.msg, "error");
            }
        }).on('fileerror', function (event, data, msg) {  //一个文件上传失败
            console.log('文件上传失败！' + data.status);
        }).on('filebatchpreupload',function (event, data) {
        })
        ;

        swal({
            title: "请注意！",
            text: "上传excel将清空所有会员信息，并以当前excel表中会员信息为准，请谨慎操作！",
            type: "warning",
            timer: 5000,
            showConfirmButton:false
        });
    }

    return {
        initPage: initPage
    }
}();


$().ready(function () {
    memberImport.initPage();
});
