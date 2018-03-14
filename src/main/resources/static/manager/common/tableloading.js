(function (global, factory) {
    typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory() :
        typeof define === 'function' && define.amd ? define(factory) : (global.tableloading = factory());
}(this, function () {
    'use strict';

    return {
        durationTime: 0, //持续时间
        time: 0,

        dialog: function (options, callback) {
            //错误提示
            if (!options) {
                console.log('请检查配置');
            }

            //生成DOM插入
            var html = '';
            html += '<div class="content-tip">' +
                '<span>' + options.message + '</span>' +
                '</div>';

            //如果存在就先删除
            if (document.querySelector('.content-tip')) {
                document.body.removeChild(document.querySelector('.content-tip'));
            }

            document.body.insertAdjacentHTML('beforeend', html);
        },
        hide: function () {
            if (document.querySelector('.content-tip')) {
                document.querySelector(".content-tip").parentNode.removeChild(document.querySelector('.content-tip'));
            }
        }
    }
}));
