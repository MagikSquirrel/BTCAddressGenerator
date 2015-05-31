
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Wallet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class main {
    
    public static void main(String[] args) throws Exception {
       
        
        //Read the wallet file
        File fWallet = new File("test.wallet");
        if(!fWallet.exists()) {

            //network
            NetworkParameters net = NetworkParameters.fromID(NetworkParameters.ID_TESTNET);            
            
            //Add new key
            ECKey key = new ECKey();            
            Address add = key.toAddress(net);
            
            //Make new wallet.
            Wallet w = new Wallet(net);
            w.importKey(key);
            
            try {
                w.saveToFile(fWallet);
            }
            catch (IOException e) {
                System.out.println("Can't save wallet file yo.");
            }
        }
        
        //Read the wallet
        Wallet w = Wallet.loadFromFile(fWallet);
        NetworkParameters net = w.getNetworkParameters();
        
        //Print out all addressed.
        List<ECKey> lKeys = w.getImportedKeys();
        for(ECKey k : lKeys) {
            
            Address a = k.toAddress(net);            
            System.out.println("Wallet owns key: "+a.toString());         
        }
        
        Coin c = w.getBalance();
        
        System.out.println("The balance of this wallet: "+c.toFriendlyString());
        
        
        
                
        
    }
    
    
}
