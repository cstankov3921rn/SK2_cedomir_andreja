

function dodajSastojak(id){
    document.querySelector(`#spisak-sastojaka > option[value='${id}']`).disabled = true;
    var naziv = document.querySelector(`#spisak-sastojaka > option[value='${id}']`).innerHTML;

    var span = document.createElement("span");
        span.classList.add("badge");
        span.classList.add("bg-secondary");
        span.dataset.id = id;
        span.innerHTML = naziv;

    var button = document.createElement("button");
        button.type="button";
        button.classList.add("btn");
        button.classList.add("btn-default");
        button.classList.add("btn-sm");
        button.innerHTML = "X";
        button.addEventListener("click", function(){
            var id = this.parentNode.dataset.id;
            this.parentNode.parentNode.removeChild( this.parentNode );
            document.querySelector(`#spisak-sastojaka > option[value='${id}']`).disabled = false;
        });

    span.appendChild(button);
    document.getElementById("sastojci").appendChild(span);
    document.getElementById("sastojci").appendChild(document.createTextNode(" "));

    document.getElementById("spisak-sastojaka").selectedIndex = 0;
}
window.addEventListener("load", function(){
	document.getElementById("forma").addEventListener("submit", function(e){

    	if( document.getElementById("naziv").value.length < 3 ){
            document.getElementById("naziv").classList.add("error");
            e.preventDefault();
            return false;
        }
        else {
            document.getElementById("naziv").classList.remove("success");
        }
        return true;
    });
    document.getElementById("naziv").addEventListener("keypress", function(){
    		this.classList.remove('success');
    this.classList.remove('error');
    });

    document.getElementById("dodaj-sastojak").addEventListener("click", function(){
            var id = document.getElementById("spisak-sastojaka").value;
            if(!id){
                alert("Izaberi sastojak");
                return;
            }
            dodajSastojak( id );
    });
});