package classes.controllers;

import java.util.HashMap;
import java.util.Map;

import classes.models.Item;
import exceptions.SoldOutException;

public class VendingStorage {
		private Map<Item,Integer> storageMap=new HashMap<>();
		
		
		
		public Map<Item, Integer> getStorageMap() {
			return storageMap;
		}

		public void setStorageMap(Map<Item, Integer> storageMap) {
			this.storageMap = storageMap;
		}

		public void addItm(Item it) {
			if(storageMap.containsKey(it)) {
				storageMap.put(it, storageMap.get(it)+1);
			}
			else {
				storageMap.put(it, 1);
			}
		}
		
		public void addMultipleItm(Item it,Integer nr) {
			if(storageMap.containsKey(it)) {
				storageMap.put(it, storageMap.get(it)+nr);
			}
			else {
				storageMap.put(it, nr);
			}
		}
		
		public void removeItm(Item it) throws SoldOutException {
			if(storageMap.containsKey(it)) {
				storageMap.put(it, storageMap.get(it)-1);
				if(storageMap.get(it)==0) {
					storageMap.remove(it);
				}
			}
			else {
				throw new SoldOutException("Item not in storage!");
			}
		}
		
		public void removeMultipleItm(Item it,Integer nr) throws SoldOutException {
			if(storageMap.containsKey(it)) {
				if(storageMap.get(it)>=nr) {
					storageMap.put(it, storageMap.get(it)-nr);
				}else {
					System.out.println("Not enough items in storage.");
				}
				
				if(storageMap.get(it)==0) {
					storageMap.remove(it);
				}
			}
			else {
				throw new SoldOutException("Item not in storage!");
			}
		}
		public Integer getNrOfItms(Item it) {
			return storageMap.get(it);
		}
		public void printItms() {
			for(Item it:storageMap.keySet()) {
				System.out.println(it);
			}
		}
		
}
