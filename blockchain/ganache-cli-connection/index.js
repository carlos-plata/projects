var Web3 = require('web3')	// require web3 library

var url = 'HTTP://127.0.0.1:8545' // ganache-cli URL (port 7545 ganache-gui)
	
var web3 = new Web3(url)	// creating web3 var

web3.eth.getAccounts().then(accounts => console.log(accounts));	// getting accounts
