Modify the Bundle to add your Search Parameter definitions.  

An example as follows: 

{
	"resourceType": "Bundle",
	"id": "searchParams",
	"meta": {
		"lastUpdated": "2019-07-12T22:37:54.724+11:00"
	},
	"type": "collection",
	"entry": [{
		"fullUrl": "http://ibm.com/watsonhealth/fhir/SearchParameter/Patient-favorite-color",
		"resource": {
			"resourceType": "SearchParameter",
			"id": "Patient-favorite-color",
			"url": "http://ibm.com/watsonhealth/fhir/SearchParameter/Patient-favorite-color",
			"version": "4.0.0",
			"name": "favorite-color",
			"status": "draft",
			"experimental": false,
			"date": "2018-12-27T22:37:54+11:00",
			"publisher": "IBM Watson Health FHIR Test",
			"contact": [{
				"telecom": [{
					"system": "url",
					"value": "http://ibm.com/watsonhealth/fhir"
				}]
			},
			{
				"telecom": [{
					"system": "url",
					"value": "http://ibm.com/watsonhealth/fhir"
				}]
			}],
			"description": "the patient's favorite color",
			"code": "favorite-color",
			"base": ["Patient"],
			"type": "string",
			"xpathUsage": "normal",
			"xpath": "f:Patient/f:extension[@url='http://ibm.com/watsonhealth/fhir/extension/Patient/favorite-color']/f:valueString",
			"expression": "Patient.extension.where(url='http://ibm.com/watsonhealth/fhir/extension/Patient/favorite-color').valueString",
			"multipleOr": true,
			"multipleAnd": true,
			"modifier": []
		}
	}]
}
