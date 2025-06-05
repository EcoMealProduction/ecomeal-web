
import React from 'react';
import { Card, CardContent } from '@/components/ui/card';
import { MapPin, DollarSign, Clock, Leaf, Heart, Shield } from 'lucide-react';

const features = [
  {
    icon: MapPin,
    title: 'Find Food Nearby',
    description: 'Discover surplus food from cafes, bakeries, and restaurants in your neighborhood with our smart location-based search.',
    color: 'bg-green-100 text-green-600'
  },
  {
    icon: DollarSign,
    title: 'Save Up to 70%',
    description: 'Get delicious, high-quality food at incredible discounts. Save money while making a positive environmental impact.',
    color: 'bg-blue-100 text-blue-600'
  },
  {
    icon: Clock,
    title: 'Real-Time Updates',
    description: 'Get instant notifications about new offers, pickup times, and availability. Never miss a great deal again.',
    color: 'bg-orange-100 text-orange-600'
  },
  {
    icon: Leaf,
    title: 'Fight Food Waste',
    description: 'Join the movement against food waste. Every meal you save helps reduce environmental impact and supports sustainability.',
    color: 'bg-green-100 text-green-600'
  },
  {
    icon: Heart,
    title: 'Community Impact',
    description: 'Track your personal impact and see how your actions contribute to a more sustainable community ecosystem.',
    color: 'bg-pink-100 text-pink-600'
  },
  {
    icon: Shield,
    title: 'Safe & Secure',
    description: 'Secure payments, verified partners, and clear food safety information ensure a trustworthy experience every time.',
    color: 'bg-purple-100 text-purple-600'
  }
];

export const FeaturesSection = () => {
  return (
    <section id="features" className="py-20 bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl font-bold text-gray-900 mb-4">
            Why Choose EcoMeal?
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            We're making it easier than ever to save money, reduce waste, and make a positive impact on our planet.
          </p>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {features.map((feature, index) => {
            const Icon = feature.icon;
            return (
              <Card key={index} className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 hover:-translate-y-2">
                <CardContent className="p-8">
                  <div className={`w-16 h-16 ${feature.color} rounded-2xl flex items-center justify-center mb-6`}>
                    <Icon size={32} />
                  </div>
                  <h3 className="text-xl font-bold text-gray-900 mb-4">
                    {feature.title}
                  </h3>
                  <p className="text-gray-600 leading-relaxed">
                    {feature.description}
                  </p>
                </CardContent>
              </Card>
            );
          })}
        </div>
      </div>
    </section>
  );
};
