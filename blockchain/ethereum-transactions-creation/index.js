/*##########################

CONFIGURATION
##########################*/

var Web3 = require("web3");
var EthereumTransaction = require("ethereumjs-tx"); 
var web3 = new Web3('HTTP://127.0.0.1:7545');

var sendingAddress = 'SENDER ADDRESS'; 
var receivingAddress = 'RECEIVER ADDRESS';

web3.eth.getBalance(sendingAddress).then(console.log);
web3.eth.getBalance(receivingAddress).then(console.log);

/*##########################

CREATE A TRANSACTION
##########################*/

var rawTransaction = { 
	nonce: 0, 
	to: receivingAddress, 
	gasPrice: 20000000, 
	gasLimit: 30000, 
	value: 100000, 
	data: "" 
};

rawTransaction;

web3.eth.getBalance(sendingAddress).then(console.log); 
web3.eth.getBalance(receivingAddress).then(console.log);

/*##########################

Sign the Transaction
##########################*/

var privateKeySender = 'SENDER PRIVATE KEY'; 
var privateKeySenderHex = new Buffer(privateKeySender, 'hex');
var transaction = new EthereumTransaction(rawTransaction);
transaction.sign(privateKeySenderHex);

/*#########################################

Send the transaction to the network
#########################################*/

var serializedTransaction = transaction.serialize(); 
web3.eth.sendSignedTransaction(serializedTransaction);