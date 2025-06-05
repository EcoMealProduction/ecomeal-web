
import React from 'react';
import { Search, ShoppingBag, MapPin, Clock, Leaf, Users } from 'lucide-react';
import image1 from '@/assets/hiw1.jpg';
import image2 from '@/assets/hiw2.webp';
import image3 from '@/assets/hiw3.png';

const steps = [
  {
    step: '01',
    icon: Search,
    title: 'Descoperă Oferte',
    description: 'Caută în aplicație mâncarea disponibilă de la restaurante, cafenele și magazine din apropierea ta. Filtrează după tipul de mâncare, preț și distanță.',
    features: ['Căutare locală în timp real', 'Filtre personalizate', 'Notificări pentru oferte noi'],
    image: image1,
    bgColor: 'from-green-50 to-green-100'
  },
  {
    step: '02',
    icon: ShoppingBag,
    title: 'Rezervă Rapid',
    description: 'Ai găsit ceva delicios? Rezervă cu câteva atingeri și plătește securizat prin aplicație. Fără taxe ascunse.',
    features: ['Plată securizată', 'Confirmare instantanee', 'Suport 24/7'],
    image: image2,
    bgColor: 'from-orange-50 to-orange-100'
  },
  {
    step: '03',
    icon: MapPin,
    title: 'Ridică și Savurează',
    description: 'Mergi la locație în timpul programului de ridicare și bucură-te de masa ta delicioasă având un impact pozitiv asupra mediului.',
    features: ['Navigare GPS', 'Program flexibil', 'Impact măsurabil'],
    image: image3,
    bgColor: 'from-blue-50 to-blue-100'
  }
];

const stats = [
  { icon: Users, number: '50K+', label: 'Utilizatori activi', color: 'text-green-600' },
  { icon: Leaf, number: '2.8T', label: 'CO₂ economisit', color: 'text-blue-600' },
  { icon: ShoppingBag, number: '125K+', label: 'Mese salvate', color: 'text-orange-600' },
];

export const HowItWorksSection = () => {
  return (
    <section id="how-it-works" className="py-20 bg-gradient-to-br from-eco-bg via-white to-green-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        {/* Header */}
        <div className="text-center mb-16">
          <div className="inline-flex items-center justify-center w-16 h-16 bg-green-500 text-white rounded-2xl mb-6 animate-bounce-in">
            <Clock size={32} />
          </div>
          <h2 className="text-4xl md:text-5xl font-bold text-gray-900 mb-6">
            Cum Funcționează{' '}
            <span className="text-green-500">EcoMeal</span>
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto leading-relaxed">
            Salvarea mâncării și economisirea banilor nu a fost niciodată mai ușoară. 
            Începe în doar trei pași simpli și fă o diferență pentru planeta noastră.
          </p>
        </div>

        {/* Steps */}
        <div className="space-y-20 mb-20">
          {steps.map((step, index) => {
            const Icon = step.icon;
            const isEven = index % 2 === 1;
            
            return (
              <div key={index} className={`flex flex-col ${isEven ? 'lg:flex-row-reverse' : 'lg:flex-row'} gap-12 items-center`}>
                {/* Content */}
                <div className="flex-1 text-center lg:text-left">
                  <div className={`inline-flex items-center justify-center w-20 h-20 bg-gradient-to-br ${step.bgColor} text-green-600 rounded-3xl mb-6 shadow-lg`}>
                    <Icon size={36} />
                  </div>
                  
                  <div className="mb-4">
                    <span className="text-green-500 font-bold text-lg tracking-wider">PASUL {step.step}</span>
                  </div>
                  
                  <h3 className="text-3xl md:text-4xl font-bold text-gray-900 mb-6">
                    {step.title}
                  </h3>
                  
                  <p className="text-xl text-gray-600 leading-relaxed mb-8">
                    {step.description}
                  </p>

                  {/* Features list */}
                  <div className="space-y-3">
                    {step.features.map((feature, featureIndex) => (
                      <div key={featureIndex} className="flex items-center gap-3 text-gray-700">
                        <div className="w-2 h-2 bg-green-500 rounded-full"></div>
                        <span className="font-medium">{feature}</span>
                      </div>
                    ))}
                  </div>
                </div>

                {/* Image */}
                <div className="flex-1">
                  <div className="relative group">
                    <div className={`bg-gradient-to-br ${step.bgColor} rounded-3xl p-8 transform group-hover:scale-105 transition-all duration-500 shadow-xl`}>
                      <img
                        src={step.image}
                        alt={step.title}
                        className="w-full h-80 object-cover rounded-2xl shadow-lg"
                      />
                    </div>
                    
                    {/* Step number overlay */}
                    <div className="absolute -top-6 -left-6 w-20 h-20 bg-green-500 text-white rounded-full flex items-center justify-center text-2xl font-bold shadow-xl border-4 border-white">
                      {step.step}
                    </div>

                    {/* Floating badge */}
                    <div className="absolute -bottom-4 -right-4 bg-white rounded-2xl p-4 shadow-xl border border-gray-100">
                      <div className="flex items-center gap-2">
                        <div className="w-3 h-3 bg-green-500 rounded-full animate-pulse"></div>
                        <span className="text-sm font-medium text-gray-700">În timp real</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
};
