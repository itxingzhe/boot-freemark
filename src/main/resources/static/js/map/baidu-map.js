function initializeBmap() {
    // 百度地图API功能
    var map = new BMap.Map("allmap", {enableMapClick: false});    // 创建Map实例
    map.centerAndZoom(new BMap.Point(119.30495233371025, 26.105880034475164), 11);  // 初始化地图,设置中心点坐标和地图级别
    var options = {
        onSearchComplete: function (results) {
            // 判断状态是否正确
            if (local.getStatus() == BMAP_STATUS_SUCCESS) {
                var s = [];
                for (var i = 0; i < results.getCurrentNumPois(); i++) {
                    s.push(results.getPoi(i).title + ", " + results.getPoi(i).address + "," + results.getPoi(i).point.lng + "," + results.getPoi(i).point.lat);
                }
                document.getElementById("r-result").innerHTML = s.join("<br/>");
            }
        }
    };
    var local = new BMap.LocalSearch("天津市", options);
    local.search("消防队");
}

function localBmapScript() {
    var script = window.document.createElement("script");
    script.type = "text/javascript";
    script.src = "http://api.map.baidu.com/api?v=2.0&ak=M2iMmqRqUj7ZeonOfFfpqg0SHl4VQG3Q&callback=initializeBmap";
    //script.src = "http://api.map.baidu.com/getscript?v=2.0&ak=M2iMmqRqUj7ZeonOfFfpqg0SHl4VQG3Q&services=&t=20181206101654";
    window.document.body.appendChild(script);
}