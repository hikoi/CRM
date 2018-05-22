var app = angular.module('crm', ['ui.router', 'oc.lazyLoad']);
//初始化路由
app.config(function($stateProvider){
    $stateProvider.state('account', {
        url            : '/account/index',
        templateUrl : '/page/backstage/account/index',
        resolve       : {
            deps : ['$ocLazyLoad', function($ocLazyLoad){
                return $ocLazyLoad.load([
                    '//cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css',
                    '/commons/css/backstage/backstage.css',
                    '//cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js',
                    '/commons/js/backstage/account/index.js'
                ]);
            }]
        }
    }).state('wechat', {
        url            : '/wechat/index',
        templateUrl : '/page/backstage/wechat/index'
    });
});

var index = index || {
    userInfo: function(){
        var accountId = sessionStorage.getItem('account_id');

        if(accountId == null || accountId == undefined || accountId == ''){
            location.href = '/page/login';
            return false;
        }

        $.ajax({
            url         : '/api/1.0/user/' + accountId,
            type       : 'GET',
            dataType : 'json',
            success   : function(res){
                if(res.success){
                    //缓存
                    sessionStorage.setItem('name', res.result.name);
                    sessionStorage.setItem('nickname', res.result.nickname);
                    sessionStorage.setItem('headImgUrl', res.result.headImgUrl);
                    sessionStorage.setItem('sex', res.result.sex);

                    //设置页面信息
                    $('.headImgUrl').attr('src', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526814181974&di=7196b6a22bd17ba7274198ef25a7e017&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F59%2F55%2F67n58PICB7J_1024.png');
                    $('.name').text(res.result.name + ' [' + res.result.nickname + ']');
                }else{
                    alert(res.msg);
                }
            },
            error      : function(response){
                console.error(response);
            }
        });
    },
    navHandler: function(){
        var isShow = $('.menu').hasClass('show');

        if(isShow){
            $('.menu').removeClass('show');
            $('.menu').addClass('hide');
            $('.content').addClass('full');
        }else{
            $('.menu').removeClass('hide');
            $('.menu').addClass('show');
            $('.content').removeClass('full');
        }
    },

    menuHandler: function(){
        var width = document.documentElement.offsetWidth || document.body.offsetWidth;

        if(width <= 767){
            $('.menu').removeClass('show');
            $('.menu').addClass('hide');
        }
    },

    resizeHandler: function(){
        // var width = document.documentElement.offsetWidth || document.body.offsetWidth;

        var isShow = $('.menu').hasClass('show');

        if(isShow){
            $('.content').removeClass('full');
        }else{
            $('.content').addClass('full');
        }
    },

    init: function(){
        //获取用户信息
        index.userInfo();

        //绑定事件
        $('.brand-toggle').bind('click', index.navHandler);
        $('.menu-side .item').bind('click', index.menuHandler);
        $(window).resize(index.resizeHandler);
    }
};

$(function(){
    index.init();
});