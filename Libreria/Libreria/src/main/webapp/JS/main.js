var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function(){
	$(function () {
  		$('[data-toggle="tooltip"]').tooltip()
	});
	
	getUsuario().then(function(){
		$("#user-saldo").html(user.saldo);
		
		
		getLibros(false,"ASC");
		
		$("#ordenar-genero").click(ordenarLibros);
	});
	
});

// llamada asincrona para obtener  el usuario 
async function getUsuario(){
		await $.ajax({
			type:"GET",
			dataType:"html",
			url:"./ServletUsuarioPedir",
			data: $.param({
				username:username,
			}),
			success: function(r){
				// parseo el resultado del controller
				let parse = JSON.parse(r);
				
				if ( parse != false ){
					user = parse;
				}else{
					console.log("Error recuperar datos user");
				}
			}
		});
}


function getLibros(ordenar,orden){
	$.ajax({
			type:"GET",
			dataType:"html",
			url:"./ServletLibroListar",
			data: $.param({
				ordenar:ordenar,
				orden:orden
			}),
			success: function(r){
				// parseo el resultado del controller
				let parse = JSON.parse(r);
				
				if ( parse != false ){
					mostrarLibros(parse);
				}else{
					console.log("Error recuperar datos de los libros");
				}
			}
		});
}

function mostrarLibros(libros){
	let contenido = "";
	
	$.each(libros,function(index,libro){
		libro = JSON.parse(libro);
		
		let precio;
		
		if (libro.copias > 0){
			if(user.premium){
				
				if(libro.novedad){
					precio = (2 -  (2 * 0.3 ));
				}
				else{
					precio = (1- (1*0.1));
				}
			}
			else{
				if (libro.novedad){
					precio = 2;
				}
				else{
					precio = 1;
				}
			}
			
			contenido += '<tr><th scope="roe">' + libro.id + '</th>' +
			'<td>' + libro.titulo + '</td>' +
			'<td>' + libro.genero + '</td>' +
			'<td>' + libro.autor + '</td>' +
			'<td>' + libro.copias + '</td>' +
			'<td><input type="checkbox" name="novedad" id="novedad '+libro.id + '" disabled ';
			
			if(libro.novedad){
				contenido += 'checked';
			} 
			
			contenido += '></td>' +
			'<td>' + precio + '</td>' +
			'<td><button onclick="alquilarLibro(' +libro.id + ','+ precio + ');" class="btn btn-success"';
			
			if(user.saldo < precio){
				contenido += "disabled";
			}
			
			
			contenido += '> Reservar </button></td></tr>';
			
			
		}
	});
	
	$("#libros-tbody").html(contenido);
}



function ordenarLibros(){
	if($("#icono-ordenar").hasClass("fa-sort")){
		getLibros(true,"ASC");
		$("#icono-ordenar").removeClass("fa-sort");
		$("#icono-ordenar").addClass("fa-sort-down");
	}else if ($("#icono-ordenar").hasClass("fa-sort-down")){
		getLibros(true,"DESC");
		$("#icono-ordenar").removeClass("fa-sort-down");
		$("#icono-ordenar").addClass("fa-sort-up");
		}else if ($("#icono-ordenar").hasClass("fa-sort-up")){
		getLibros(true,"ASC");
		$("#icono-ordenar").removeClass("fa-sort-up");
		$("#icono-ordenar").addClass("fa-sort");
		
		}
}
	
