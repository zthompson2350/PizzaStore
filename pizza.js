function jsonBuilder() {
    var elements = document.getElementById("pizzaForm").elements;
    var obj = {};
    for (var i = 0; i < elements.length - 1; i++) {
        var item = elements.item(i);
        obj[item.name] = item.value;
        console.log(obj);


    }
    var json = JSON.stringify(obj);
    console.log(json);
    return json;
};



function sunbmitPress() {
    var http = new XMLHttpRequest();
    http.open("POST", 'http://localhost:8080/', true);
    http.send();
    console.log(http);


    http.onreadystatechange = function monitorState() {
        console.log(http);
        if (http.readyState == 4 && http.status == 200) {
            console.log(JSON.parse(http.response));
        }
    };
};