import React from 'react';
import { Card, CardContent } from '@/components/ui/card';
import { MapPin, Star } from 'lucide-react';
import { Link } from 'react-router-dom';
import tucano from '@/assets/partners/tucanocoffee.png'
import fivetogo from '@/assets/partners/5togo.png' 

const partners = [
  {
    logo: tucano,
    name: 'Tucano Coffee',
    rating: 4.8,
    specialties: ['Coffee']
  },
  {
    logo: fivetogo,
    name: '5 To Go',
    rating: 4.5,
    specialties: ['Coffee']
  },
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
              to={`/`}
              className="group"
            >
              <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 hover:-translate-y-2 overflow-hidden cursor-pointer">
                <div className="relative w-full h-64 p-6">
                  <img
                    src={partner.logo}
                    alt={partner.name}
                    className="w-full h-full object-contain transition-transform duration-300"
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
