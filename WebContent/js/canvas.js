/**
 * Fichier de gestion des graphiques canvas
 */

function drawProgressProfil(nbParcours, nbCursus) {
	var canvas = document.getElementById("canvas_profil_complete");
	var ctx = canvas.getContext("2d"); //Récupération du contexte 2D du canvas
	
	ctx.lineWidth = 20;
	ctx.strokeStyle = "#79b41c";
	ctx.fillStyle = "#000000";
	ctx.font = "20pt Arial";
	var posX = 100;
	var posY = 100;
	var diam = 50;
	var angleStart = 0;
	
	ctx.beginPath();
	if(nbParcours == 0 && nbCursus == 0) {
		ctx.arc(posX, posY, diam, angleStart, 0.33 * Math.PI, true);
		ctx.fillText("33%", posX, posY);
	}
	else if((nbParcours > 0 || nbCursus > 0) && !(nbParcours > 0 && nbCursus > 0)) {
		ctx.arc(posX, posY, diam, angleStart, 0.66 * Math.PI, true); //EXCLUSIVE OR
		ctx.fillText("66%", posX, posY);
	} else {
		ctx.arc(posX, posY, diam, angleStart, 2 * Math.PI, true);
		ctx.fillText("100%", posX - 35, posY + 10);
	}
	//ctx.fill();
	ctx.stroke();
}