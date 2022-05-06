package com.revature.models;

public enum ReimbType {

	FOOD {
		public String toString() {
			return "Food";
		}
	},
	LODGING {
		public String toString() {
			return "Lodging";
		}
	},
	TRAVEL {
		public String toString() {
			return "Travel";
		}
	},
	OTHER {
		@Override
		public String toString() {
			return "Other";
		}

	}
}
