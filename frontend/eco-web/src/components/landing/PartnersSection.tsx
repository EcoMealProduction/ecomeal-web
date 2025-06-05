import React from 'react';
import { Card, CardContent } from '@/components/ui/card';
import { MapPin, Star } from 'lucide-react';
import { Link } from 'react-router-dom';

const partners = [
  {
    name: 'Bella Vista Bakery',
    type: 'Bakery',
    location: 'Bucharest Center',
    rating: 4.8,
    image: 'photo-1555507036-ab794f4aacd3',
    specialties: ['Fresh Bread', 'Pastries', 'Croissants'],
    partnerId: 'bella-vista',
    offerId: '1'
  },
  {
    name: 'Green Garden Cafe',
    type: 'Cafe',
    location: 'Herastrau Park',
    rating: 4.9,
    image: 'photo-1554118811-1e0d58224f24',
    specialties: ['Coffee', 'Sandwiches', 'Salads'],
    partnerId: 'green-garden',
    offerId: '2'
  },
  {
    name: 'Metro Fresh Market',
    type: 'Supermarket',
    location: 'Multiple Locations',
    rating: 4.6,
    image: 'photo-1578662996442-48f60103fc96',
    specialties: ['Fresh Produce', 'Ready Meals', 'Dairy'],
    partnerId: 'metro-fresh',
    offerId: '3'
  },
  {
    name: 'Pasta & More',
    type: 'Restaurant',
    location: 'Old Town',
    rating: 4.7,
    image: 'photo-1555396273-367ea4eb4db5',
    specialties: ['Italian Food', 'Fresh Pasta', 'Pizza'],
    partnerId: 'pasta-more',
    offerId: '4'
  },
  {
    name: 'Sweet Dreams Patisserie',
    type: 'Patisserie',
    location: 'Calea Victoriei',
    rating: 4.9,
    image: 'photo-1578985545062-69928b1d9587',
    specialties: ['Cakes', 'Macarons', 'Desserts'],
    partnerId: 'sweet-dreams',
    offerId: '5'
  },
  {
    name: 'Farm to Table',
    type: 'Organic Market',
    location: 'Floreasca',
    rating: 4.8,
    image: 'photo-1542838132-92c53300491e',
    specialties: ['Organic Produce', 'Local Products', 'Healthy Options'],
    partnerId: 'farm-to-table',
    offerId: '6'
  }
];

export const PartnersSection = () => {
  return (
    <section id="partners" className="py-20 bg-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl font-bold text-gray-900 mb-4">
            Our Amazing Partners
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            We've partnered with the best cafes, bakeries, restaurants, and markets across the city to bring you quality food at great prices.
          </p>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {partners.map((partner, index) => (
            <Link 
              key={index} 
              to={`/marketplace/offer/${partner.offerId}`}
              className="group"
            >
              <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 hover:-translate-y-2 overflow-hidden cursor-pointer">
                <div className="relative">
                  <img
                    src={`https://images.unsplash.com/${partner.image}?w=400&h=200&fit=crop`}
                    alt={partner.name}
                    className="w-full h-48 object-cover group-hover:scale-105 transition-transform duration-300"
                  />
                  <div className="absolute top-4 right-4 bg-white/90 backdrop-blur-sm rounded-full px-3 py-1 flex items-center gap-1">
                    <Star size={16} className="fill-yellow-400 text-yellow-400" />
                    <span className="text-sm font-semibold">{partner.rating}</span>
                  </div>
                </div>
                
                <CardContent className="p-6">
                  <div className="mb-4">
                    <h3 className="text-xl font-bold text-gray-900 mb-1 group-hover:text-green-600 transition-colors">
                      {partner.name}
                    </h3>
                    <div className="flex items-center text-gray-500 text-sm">
                      <MapPin size={16} className="mr-1" />
                      {partner.location} • {partner.type}
                    </div>
                  </div>

                  <div className="flex flex-wrap gap-2">
                    {partner.specialties.map((specialty, idx) => (
                      <span
                        key={idx}
                        className="bg-green-100 text-green-700 px-3 py-1 rounded-full text-sm font-medium"
                      >
                        {specialty}
                      </span>
                    ))}
                  </div>
                </CardContent>
              </Card>
            </Link>
          ))}
        </div>

        <div className="text-center mt-12">
          <div className="bg-gray-50 rounded-2xl p-8">
            <h3 className="text-2xl font-bold text-gray-900 mb-4">
              Want to Become a Partner?
            </h3>
            <p className="text-gray-600 mb-6 max-w-2xl mx-auto">
              Join our network of sustainable food partners and help reduce food waste while reaching new customers who care about the environment.
            </p>
            <Link to="/partners">
              <button className="bg-green-600 hover:bg-green-700 text-white px-8 py-3 rounded-lg font-semibold transition-colors">
                Partner With Us
              </button>
            </Link>
          </div>
        </div>
      </div>
    </section>
  );
};
