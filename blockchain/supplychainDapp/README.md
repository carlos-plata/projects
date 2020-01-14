# Ethereeum Dapp for Tracking Items through Supply Chain

Ropsten address: 0xa6bdb7a2E212fF29d17f2696D7df4882E4506A5E  

### Program version numbers  

node: 		v10.16.3  
Truffle: 	v14.1.14
web3:		v1.2.1  
Solidity:	v.0.4.24  

## Installation (Local)
```bash
cd supplychainDapp  

npm install  

ganache-cli  

truffle compile  

truffle migrate --reset

truffle test
```

Lunch Dapp (on a separate terminal):  

```bash
npm run dev
```

## Installation (Ropsten)

```bash
cd supplychainDapp
```

### Node.js

```bash
npm install
```
### Truffle

```bash
npm install -g truffle

truffle init

npm install --save truffle-hdwallet-provider
```

Update truffle.js file with the following:  

```bash
var HDWalletProvider = require("truffle-hdwallet-provider");
const MNEMONIC = 'YOUR WALLET KEY';

module.exports = {
  networks: {
    development: {
      host: "127.0.0.1",
      port: 8545,
      network_id: "*"
    },
    ropsten: {
      provider: function() {
        return new HDWalletProvider(MNEMONIC, "https://ropsten.infura.io/YOUR_API_KEY")
      },
      network_id: 3,
      gas: 4000000
    }
  }
};
```

Deploy to Ropsten network:  

```bash
truffle deploy --network ropsten
```