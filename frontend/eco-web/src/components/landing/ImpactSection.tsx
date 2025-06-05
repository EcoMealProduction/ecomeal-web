
import React from 'react';
import { Card, CardContent } from '@/components/ui/card';

const stats = [
  {
    number: '12,847',
    label: 'Meals Saved',
    icon: '🍽️',
    description: 'Delicious meals rescued from waste'
  },
  {
    number: '5.2T',
    label: 'CO₂ Prevented',
    icon: '🌍',
    description: 'Carbon emissions avoided'
  },
  {
    number: '€85K',
    label: 'Money Saved',
    icon: '💰',
    description: 'Total savings for our community'
  },
  {
    number: '200+',
    label: 'Partner Stores',
    icon: '🏪',
    description: 'Cafes, bakeries & restaurants'
  }
];

export const ImpactSection = () => {
  return (
    <section id="impact" className="py-20 bg-gradient-to-br from-green-600 to-green-700 text-white relative overflow-hidden">
      {/* Background pattern */}
      <div className="absolute inset-0 opacity-10">
        <div className="absolute top-10 left-10 w-32 h-32 border-2 border-white rounded-full"></div>
        <div className="absolute top-40 right-20 w-24 h-24 border-2 border-white rounded-full"></div>
        <div className="absolute bottom-20 left-1/3 w-40 h-40 border-2 border-white rounded-full"></div>
      </div>

      <div className="relative z-10 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl font-bold mb-4">
            Our Community Impact
          </h2>
          <p className="text-xl text-green-100 max-w-3xl mx-auto">
            Together, we're making a real difference. See how our community is fighting food waste and building a more sustainable future.
          </p>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-8">
          {stats.map((stat, index) => (
            <Card key={index} className="bg-white/10 backdrop-blur-sm border-white/20 hover:bg-white/20 transition-all duration-300">
              <CardContent className="p-8 text-center">
                <div className="text-4xl mb-4">{stat.icon}</div>
                <div className="text-3xl lg:text-4xl font-bold mb-2">{stat.number}</div>
                <div className="text-lg font-semibold mb-2">{stat.label}</div>
                <div className="text-green-100 text-sm">{stat.description}</div>
              </CardContent>
            </Card>
          ))}
        </div>

        <div className="mt-16 text-center">
          <div className="bg-white/10 backdrop-blur-sm rounded-2xl p-8 max-w-3xl mx-auto">
            <h3 className="text-2xl font-bold mb-4">Join the Movement</h3>
            <p className="text-green-100 text-lg mb-6">
              Every meal you save contributes to a more sustainable world. Small actions, big impact.
            </p>
            <div className="flex justify-center items-center gap-4">
              <span className="text-4xl">🌱</span>
              <span className="text-xl">+</span>
              <span className="text-4xl">🤝</span>
              <span className="text-xl">=</span>
              <span className="text-4xl">🌍</span>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};
