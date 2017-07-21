var ws;

window.onload = function(){

			var lable = document.getElementById("lable");
			ws = new WebSocket("ws://localhost:8085/responseChanegeSubscribe");
			ws.onopen = function() { console.log("Connection opened...") };
			ws.onclose = function() { console.log("Connection closed...") };
			ws.onmessage = function(evt) {
				
				rowdata = JSON.parse(evt.data); 
				var row = document.getElementById("headerrow");
				
				var newrow = row.cloneNode(true);
				var mas = newrow.getElementsByTagName('label');
				var currval;
				var cl = mas[0].parentNode.getAttribute('class').split(' ')[0];
				for(var i = 0; i < mas.length; i++) {
					currval = mas[i].innerHTML;
					if(rowdata[currval] === undefined)
					{
						mas[i].innerHTML = 'N/A';
					}
					else
					{
						mas[i].innerHTML = rowdata[currval];		
					}
					mas[i].parentNode.setAttribute('class',cl);
				}
				newrow.setAttribute("id","newrow");
				row.parentNode.appendChild(newrow);
			};
		};

window.onbeforeunload = function(e) {
    ws.close();
};