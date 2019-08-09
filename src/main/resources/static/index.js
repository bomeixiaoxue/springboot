/**
 @author hao
 @date 2019-08-06 16:00
 description
 */

var tableData = [];

/**
 * 初始化html
 */
$(document).ready(function () {
    getUserList();
});

/**
 * 获取用户列表
 */
function getUserList() {
    $.ajax({
        url: '/user/',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            console.log(data);
            tableData = data.data;
            rendererTable(data.data);
        },
        error: function (data) {
            console.log(data);
        }
    });
}

/**
 * 渲染用户列表
 * @param dataArr 用户列表数组
 */
function rendererTable(dataArr) {
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#demo'
            , toolbar: '#toolbarDemo'
            , cols: [[
                {type: 'checkbox'}
                , {field: 'id', title: 'ID', sort: true}
                , {field: 'name', title: '用户名'}
                , {field: 'age', title: '年龄'}
                , {title: '操作', toolbar: '#barDemo', width: 200}
            ]]
            , data: dataArr
            // ,skin: 'line' //表格风格
            , even: true
            , page: true //是否显示分页
            , limits: [10, 30, 50]
            , limit: 10 //每页默认显示的数量
        });

        monitorTable(table);
    });
}

/**
 * 监听表格事件
 */
function monitorTable(table) {
    //监听表格复选框选择
    table.on('checkbox(demoFilter)', function (obj) {
        console.log(obj);
    });

    //监听工具条
    table.on('tool(demoFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            window.location.href = "/user/add?id=" + data.id + "&edit=0";
        } else if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                deleteUser(data, obj);
                layer.close(index);
            });

        } else if (obj.event === 'edit') {
            window.location.href = "/user/add?id=" + data.id + "&edit=1";
        }
    });

}

/**
 * 跳转到新增页面
 */
function addUser() {
    window.location.href = "/user/add?edit=1";
}

/**
 * 删除用户
 */
function deleteUser(user, obj) {
    $.ajax({
        url: '/user/' + user.id,
        type: 'DELETE',
        dataType: 'json',
        success: function (data) {
            if (data.code != "" && data.code == "0") {
                console.log(data);
                console.log(tableData);
                layer.msg("删除用户" + user.name + "成功！");
                obj.del();
                getUserList();
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}

