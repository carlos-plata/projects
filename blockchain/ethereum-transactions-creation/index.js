/*##########################

CONFIGURATION
##########################*/

// -- Step 1: Set up the appropriate configuration 
var Web3 = require("web3");
var EthereumTransaction = require("ethereumjs-tx"); 
var web3 = new Web3('HTTP://127.0.0.1:7545');

// -- Step 2: Set the sending and receiving addresses for the transaction. 
var sendingAddress = '0x391B6c531C6A27925D9aF06798700Ecc16CeD9Fc'; 
var receivingAddress = '0xb0D2e7Bf6E39138acd0064f7B82e7Dee3Cbc6c9A';

// -- Step 3: Check the balances of each address 
web3.eth.getBalance(sendingAddress).then(console.log);
web3.eth.getBalance(receivingAddress).then(console.log);

/*##########################

CREATE A TRANSACTION
##########################*/

// -- Step 4: Set up the transaction using the transaction variables as shown 
var rawTransaction = { 
	nonce: 0, 
	to: receivingAddress, 
	gasPrice: 20000000, 
	gasLimit: 30000, 
	value: 100000, 
	data: "" 
};

// -- Step 5: View the raw transaction 
rawTransaction;

// -- Step 6: Check the new account balances (they should be the same) 
web3.eth.getBalance(sendingAddress).then(console.log); 
web3.eth.getBalance(receivingAddress).then(console.log);

/*##########################

Sign the Transaction
##########################*/

// -- Step 7: Sign the transaction with the Hex value of the private key of the sender 
var privateKeySender = '6648be60fbd969bdb3a8d98dc5108454a362e842474acb7b442bed14a06fa0eb'; 
var privateKeySenderHex = new Buffer(privateKeySender, 'hex');
var transaction = new EthereumTransaction(rawTransaction);
transaction.sign(privateKeySenderHex);

/*#########################################

Send the transaction to the network
#########################################*/

// -- Step 8: Send the serialized signed transaction to the Ethereum network. 
var serializedTransaction = transaction.serialize(); 
web3.eth.sendSignedTransaction(serializedTransaction);

