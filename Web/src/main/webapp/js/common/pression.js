var menuIds;


function justPression() {
    var localTest = layui.data('pression');
    if (localTest == undefined) {

    } else {
        menuIds = localTest.menuIds;
    }
}

function justPressionBy(menuid) {
    return isInArray3(menuIds, menuid);

}

function isInArray3(arr,value){
    return $.inArray(value,arr)>-1;

    // var testStr=','+arr.join(",")+",";
    // return testStr.indexOf(","+value+",")!=-1;
}